import "../styles/NavigationBar.css"

import Container from "react-bootstrap/Container"
import Nav from "react-bootstrap/Nav"
import Navbar from "react-bootstrap/Navbar"

import fdm_logo from "../images/fdm_logo.png"

const NavigationBar = () => {
    return (
        <Navbar>
            <Container fluid id="navbar">
                <Navbar.Brand href="#home">
                    <img id="fdm-logo" src={fdm_logo} alt="FDM"></img>
                </Navbar.Brand>
                <div id="navbar-actions">
                        <Nav.Link href="#submit-claim">Submit Claim</Nav.Link>
                        <Nav.Link href="#home">Logout</Nav.Link>
                    </div>
            </Container>
        </Navbar>
    )
}

export default NavigationBar