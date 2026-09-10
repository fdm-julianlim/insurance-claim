import "../styles/NavigationBar.css"

import Container from "react-bootstrap/Container"
import Nav from "react-bootstrap/Nav"
import Navbar from "react-bootstrap/Navbar"
import { Link } from "react-router-dom"

import fdm_logo from "../images/fdm_logo.png"

const NavigationBar = () => {
    return (
        <Navbar>
            <Container fluid id="navbar">
                <Navbar.Brand as={Link} to="/">
                    <img id="fdm-logo" src={fdm_logo} alt="FDM"></img>
                </Navbar.Brand>
                <div id="navbar-actions">
                    <Nav.Link as={Link} to="/submit-claim">Submit Claim</Nav.Link>
                    <Nav.Link as={Link} to="/claim-history">Claim History</Nav.Link>
                    <Nav.Link as={Link} to="/">Coverage</Nav.Link>
                </div>
            </Container>
        </Navbar>
    )
}

export default NavigationBar