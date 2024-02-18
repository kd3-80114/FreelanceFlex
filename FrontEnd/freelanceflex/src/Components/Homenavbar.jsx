import { NavDropdown } from 'react-bootstrap'
import { Link, useNavigate } from 'react-router-dom'
import flexImage from '../flex.jpeg'
import backgroundImg from '../HomeBackground.jpeg';

export function Homenavbar() {
  const navigate = useNavigate()

  const onSignIn = () => {
    navigate('/signin')
  }

  return (
    <div style={{ 
        backgroundImage: `url(${backgroundImg})`, 
        backgroundSize: 'cover', 
        minHeight: '100vh', 
        paddingTop: '50px', 
      }}>
      <nav className='navbar navbar-expand-lg bg-primary' data-bs-theme='dark'>
        <div className='container-fluid'>
          {/* Logo */}
          <Link className='navbar-brand' to='/'>
            {/* <img src={flexImage} alt='Your Logo' style={{ maxWidth: '100px' }} /> */}
           <em style={{ fontSize: '1.5em', color: '' }}>FreelanceFlex</em>
          </Link>

          {/* Navbar toggler for mobile */}
          <button
            className='navbar-toggler'
            type='button'
            data-bs-toggle='collapse'
            data-bs-target='#navbarSupportedContent'
            aria-controls='navbarSupportedContent'
            aria-expanded='false'
            aria-label='Toggle navigation'
          >
          <span className='navbar-toggler-icon'></span>
          </button>

          {/* Navbar links */}
          <div className='collapse navbar-collapse justify-content-end' id='navbarSupportedContent'>
            <ul className='navbar-nav'>
              <li className='nav-item'>
                <Link className='nav-link' aria-current='page' to='/signup'>
                  Become a freelancer
                </Link>
              </li>
              <li className='nav-item'>
                <Link className='nav-link' aria-current='page' to='/signup'>
                  Become a buyer
                </Link>
              </li>
              <li className='nav-item'>
                <button onClick={onSignIn} className='nav-link' aria-current='page'>
                  SignIn
                </button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className='text-center' style={{ marginTop: '200px'}}>
    <div>
      <em style={{ fontSize: '2em', color: '' }}><h2 style={{ fontSize: '2em', color: '' }}>Find the right freelance service, right away</h2></em>
    </div>
    </div>
    </div>
  )
}

export default Homenavbar
