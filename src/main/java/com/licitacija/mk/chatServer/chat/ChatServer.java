//package com.licitacija.mk.chatServer.chat;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.licitacija.mk.chatServer.message.Message;
//import com.licitacija.mk.chatServer.message.MessageType;
//import com.licitacija.mk.chatServer.user.User;
//import com.licitacija.mk.mongoModels.Offer;
//import com.licitacija.mk.mongoModels.OfferMongoRepository;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.java_websocket.WebSocket;
//import org.java_websocket.handshake.ClientHandshake;
//import org.java_websocket.server.WebSocketServer;
//
//import javax.persistence.*;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name="chat_server")
//public class ChatServer extends WebSocketServer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long chatServerId;
//
//    @Transient
//    private final static Logger logger = LogManager.getLogger(ChatServer.class);
//    @Transient
//    private HashMap<WebSocket, User> users;
//    @Transient
//    private Set<WebSocket> conns;
//
//    @Transient
//    private final OfferMongoRepository repository;
//
//    public ChatServer(int port, OfferMongoRepository repository) {
//        super(new InetSocketAddress(port));
//        this.repository = repository;
//        conns = new HashSet<>();
//        users = new HashMap<>();
//
//    }
//
//    public long getChatServerId() {
//        return chatServerId;
//    }
//
//    public void setChatServerId(long chatServerId) {
//        this.chatServerId = chatServerId;
//    }
//
//    public HashMap<WebSocket, User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(HashMap<WebSocket, User> users) {
//        this.users = users;
//    }
//
//    public Set<WebSocket> getConns() {
//        return conns;
//    }
//
//    public void setConns(Set<WebSocket> conns) {
//        this.conns = conns;
//    }
//
//    @Override
//    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
//        conns.add(webSocket);
//
//        logger.info("Connection established from: " + webSocket.getRemoteSocketAddress().getHostString());
//    }
//
//    @Override
//    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
//        conns.remove(conn);
//        // When connection is closed, remove the user.
//        try {
//            removeUser(conn);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        logger.info("Connection closed to: " + conn.getRemoteSocketAddress().getHostString());
//    }
//
//
//    @Override
//    public void onMessage(WebSocket conn, String message) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Message msg = mapper.readValue(message, Message.class);
//
//            switch (msg.getType()) {
//                case USER_JOINED:
//                    addUser(new User(msg.getUser().getName()), conn);
//                    break;
//                case USER_LEFT:
//                    removeUser(conn);
//                    break;
//                case TEXT_MESSAGE:
//                    Offer offer = new Offer(Integer.parseInt(msg.getData()));
//                    repository.save(offer);
//                    broadcastMessage(msg);
//            }
//
//            logger.info("Message from user: " + msg.getUser() + ", text: " + msg.getData());
//        } catch (IOException e) {
//            logger.error("Wrong message format.");
//            // return error message to user
//        }
//    }
//
//    @Override
//    public void onError(WebSocket conn, Exception ex) {
//
//        if (conn != null) {
//            conns.remove(conn);
//        }
//        assert conn != null;
//        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
//    }
//
//    private void broadcastMessage(Message msg) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String messageJson = mapper.writeValueAsString(msg);
//            for (WebSocket sock : conns) {
//                sock.send(messageJson);
//            }
//        } catch (JsonProcessingException e) {
//            logger.error("Cannot convert message to json.");
//        }
//    }
//
//    private void addUser(User user, WebSocket conn) throws JsonProcessingException {
//        users.put(conn, user);
//        acknowledgeUserJoined(user, conn);
//        broadcastUserActivityMessage(MessageType.USER_JOINED);
//    }
//
//    private void removeUser(WebSocket conn) throws JsonProcessingException {
//        users.remove(conn);
//        broadcastUserActivityMessage(MessageType.USER_LEFT);
//    }
//
//    private void acknowledgeUserJoined(User user, WebSocket conn) throws JsonProcessingException {
//        Message message = new Message();
//        message.setType(MessageType.USER_JOINED_ACK);
//        message.setUser(user);
//        conn.send(new ObjectMapper().writeValueAsString(message));
//    }
//
//    private void broadcastUserActivityMessage(MessageType messageType) throws JsonProcessingException {
//
//        Message newMessage = new Message();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String data = mapper.writeValueAsString(users.values());
//        newMessage.setData(data);
//        newMessage.setType(messageType);
//        broadcastMessage(newMessage);
//    }
//}
