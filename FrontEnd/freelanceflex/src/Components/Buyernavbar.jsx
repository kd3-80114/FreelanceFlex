import { NavDropdown } from 'react-bootstrap'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { viewBuyerReviews } from '../Services/Buyer'

export function Buyernavbar() {
  const navigate = useNavigate()
  const onLogout = () => {
    sessionStorage.removeItem('Authorization')
    sessionStorage.removeItem('currentUser')
    sessionStorage.removeItem('BuyerReviews')
    navigate('/signin')
  }
  const viewReviews = async () => {

    var currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    console.log(currentUser.id)
    const result = await viewBuyerReviews(currentUser.id)
  

    if (result.status==200) {
        console.log(result.data)
      sessionStorage['BuyerReviews'] = JSON.stringify(result.data)
        } else {
      toast.warn("Please try again later")
    }
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
      </Link>
    </li>
    <Link className='nav-link' aria-current='page' to='/'>

      </Link>
    <NavDropdown title="Order" id="orders-dropdown">
      {/* Add your Gigs dropdown items here */}
      <NavDropdown.Item href='/buyer/placeorder'>Place Order</NavDropdown.Item>
      <NavDropdown.Item href='/buyer/vieworders'>View Orders</NavDropdown.Item>
      {/* Add more items as needed */}
    </NavDropdown>
    <NavDropdown title="Review" id="reviews-dropdown">
      {/* Add your Gigs dropdown items here */}
      <NavDropdown.Item href='/buyer/createreview'>Create Review</NavDropdown.Item>
      {/* <NavDropdown.Item href='/buyer/viewreviews' onClick={viewReviews}>View Reviews</NavDropdown.Item> */}
      <NavDropdown.Item href='/buyer/viewReviews' onClick={viewReviews}>View Reviews</NavDropdown.Item>
      {/* Add more items as needed */}
    </NavDropdown>
    <Link className='nav-link' aria-current='page' to='/buyer/viewProfile'>
      View Profile
      </Link>
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

export default Buyernavbar
