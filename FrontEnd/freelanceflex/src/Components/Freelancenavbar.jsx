import { NavDropdown } from 'react-bootstrap'
import { Link, useNavigate } from 'react-router-dom'

export function Freelancernavbar() {
  const navigate = useNavigate()

  const onLogout = () => {
    sessionStorage.removeItem('Authorization')
    sessionStorage.removeItem('currentUser')
    sessionStorage.removeItem('BuyerReviews')
    navigate('/signin')
  }
  
  return (
    <nav className='navbar navbar-expand-lg bg-primary' data-bs-theme='dark'>
  <div className='container-fluid'>
    {/* Logo */}
    <Link className='navbar-brand' to='/'>
      Your Logo
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
      <Link className='nav-link' aria-current='page' to='/'>
        Home
      </Link>
    </li>
    <Link className='nav-link' aria-current='page' to='/'>
        Orders
      </Link>
    <NavDropdown title="Gigs" id="gigs-dropdown">
      {/* Add your Gigs dropdown items here */}
      <NavDropdown.Item href='/freelancer/creategig'>Create Gig</NavDropdown.Item>
      <NavDropdown.Item href='/freelancer/viewgigs'>View Gigs</NavDropdown.Item>
      {/* Add more items as needed */}
    </NavDropdown>
    
    <NavDropdown title="Profile" id="profile-dropdown">
      {/* Add your Profile dropdown items here */}
      <NavDropdown.Item href='#'>Edit Profile</NavDropdown.Item>
      <NavDropdown.Item href='#'>View Profile</NavDropdown.Item>
      {/* Add more items as needed */}
    </NavDropdown>
    <li className='nav-item'>
      <button onClick={onLogout} className='nav-link' aria-current='page'>
        Logout
      </button>
    </li>
  </ul>
</div>
  </div>
</nav>

  )
}

export default Freelancernavbar
