import React, {Component} from 'react';
import {Client} from "@stomp/stompjs";

class Chat extends Component {

    state = {
        serverTime: null,
        userName: ""
    };


    componentDidMount() {
        this.setState({room: 'neco'});
        this.client = new Client();

        this.client.configure({

            brokerURL: 'ws://localhost:8080/stomp',
            onConnect: () => {

                this.client.subscribe('/queue/now', message => {
                    console.log(message);
                    this.setState({serverTime: message.body});
                });

                this.client.subscribe(`/topic/${this.state.room}`, message => {
                    alert(message.body);
                    let lelke = document.getElementById('lel');
                    lelke.innerHTML += `<p>${this.state.room} : ${message.body} </p>`
                });
            }
        });

        this.client.activate();

    }

    clickHandler = () => {
        let lel = this.refs.title.value;
        this.client.publish({destination: `/app/${this.state.room}`, body: lel});
        console.log(this.state.room);

    };

    changeRoom = (room1) => {
        this.setState({room: room1.target.innerHTML});
        this.client.deactivate();

        this.client = new Client();

        this.client.configure({

            brokerURL: 'ws://localhost:8080/stomp',
            onConnect: () => {

                this.client.subscribe('/queue/now', message =>  {
                    this.setState({serverTime: message.body});
                });

                this.client.subscribe(`/topic/${this.state.room}`, message => {
                    alert(message.body);
                    let lelke = document.getElementById('lel');
                    lelke.innerHTML += `<p>${this.state.room} : ${message.body} </p>`
                });
            }
        });

        this.client.activate();

    };
    // login = (e) => {
    //     e.preventDefault();
    //     let base64Credential = btoa(e.target.username.value + ':' + e.target.password.value);
    //
    //     fetch('http://localhost:8080/account/login', {
    //         method: 'GET',
    //         headers: {
    //             'Content-Type': 'application/json',
    //             'Authorization': 'Basic ' + base64Credential
    //         }
    //
    //     }).then(
    //         response => response.json()
    //     ).then(data => {
    //         console.table(data);
    //
    //         if (data) {
    //             localStorage.setItem('currentUser', JSON.stringify(data));
    //         }
    //         // else {
    //         //
    //         //     console.log(JSON.stringify(response));
    //         //
    //     });
    //
    // };
    // register = (e) => {
    //     e.preventDefault();
    //     let user = {
    //         username: e.target.username.value,
    //         password: e.target.password.value,
    //         fullName: e.target.fullName.value
    //     };
    //     fetch('http://localhost:8080/account/register', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify({
    //             username: user.username,
    //             password: user.password,
    //             fullName: user.fullName
    //         })
    //     }).then(
    //         response => {
    //             alert(response)
    //         }
    //     );
    // };
    //
    //
    // testSecurity = () => {
    //     let base64Credential = btoa(localStorage.getItem('currentUser').username + ':'
    //         + localStorage.getItem('currentUser').password);
    //
    //     fetch('http://localhost:8080/account/test', {
    //         method: 'GET',
    //         // headers: {
    //         //     'Content-Type': 'application/json'
    //         //    // 'Authorization' : 'Basic' + localStorage.getItem('currentUser').sesion
    //         //
    //         // }
    //
    //     }).then(
    //         response => {
    //             alert(response.toString());
    //             alert(localStorage.getItem('currentUser'))
    //         }
    //     );
    //
    // };

    render() {
        return (
            <div className="Chat">
                <p>
                    The CHAT
                </p>
                <p>
                    Server time: {this.state.serverTime ? this.state.serverTime : 'no data'}
                    {this.state.serverTime}
                </p>
                <p>

                    <input ref='title' className='test'/>
                    <button onClick={this.clickHandler}>Click me</button>
                </p>
                <p id='lel'>
                    Ovdeka poruke

                </p>

                <button className='filip' onClick={this.changeRoom}>Filip</button>
                <button className='neco' onClick={this.changeRoom}>nesto</button>


            </div>


        );
    }
}

export default Chat;
