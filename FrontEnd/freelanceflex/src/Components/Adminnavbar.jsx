import { NavDropdown } from 'react-bootstrap'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'



export function Adminnavbar()
{
    const navigate = useNavigate()
    const onLogout = () => {
      sessionStorage.removeItem('Authorization')
      sessionStorage.removeItem('currentUser')
      sessionStorage.removeItem('BuyerReviews')
      navigate('/signin')
    }


return(

  <nav className='navbar navbar-expand-lg bg-primary' data-bs-theme='dark'>
  <div className='container-fluid'>
  {/* Logo */}
  <Link className='navbar-brand' to='/'>
    <em style={{ fontSize: '1.5em', color: '' }}>FreelanceFlex</em>
    </Link>

  <div className='collapse navbar-collapse justify-content-end' id='navbarSupportedContent'>
  <ul className='navbar-nav'>
    
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

    <NavDropdown title="Find" id="gigs-dropdown">
      {/* Add your Gigs dropdown items here */}
      <NavDropdown.Item href='/buyer/ViewProfile'>View AdminProfile</NavDropdown.Item>
      <NavDropdown.Item href='/admin/buyerprofile'>View BuyerProfile</NavDropdown.Item>
      <NavDropdown.Item href='/admin/freelancerprofile'>View FreelancerProfile</NavDropdown.Item>
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
export default Adminnavbar