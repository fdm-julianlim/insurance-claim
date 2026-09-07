import fdm_logo from "../images/fdm_logo.png"

const NavigationBar = () => {
    return (
        <nav className="navbar">
            <div id="navbar-left">
                <img src={fdm_logo} alt="FDM"></img>
            </div>
        </nav>
    )
}

export default NavigationBar