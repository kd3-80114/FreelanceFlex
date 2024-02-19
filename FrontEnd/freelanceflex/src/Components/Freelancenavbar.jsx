



  

 


import React from "react";
import { NavDropdown } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { viewAllGigsOfFreelancer } from '../Services/Freelancer';

export function Freelancernavbar() {
  const navigate = useNavigate();

  const onLogout = () => {
    sessionStorage.removeItem('Authorization');
    sessionStorage.removeItem('currentUser');
    sessionStorage.removeItem('BuyerReviews');
    navigate('/signin');
  };

  const viewAllGigs = async () => {
    var user = JSON.parse(sessionStorage['currentUser']);
    var id = user.id;
    const result = await viewAllGigsOfFreelancer(id);
    navigate('/freelancer/viewgigs', { state: { gigs: result } });  
  };


  return (
    <nav className='navbar navbar-expand-lg navbar-dark bg-dark'>
      <div className='container-fluid'>
        {/* Logo */}
        <Link className='navbar-brand text-primary' to='/freelancer/freelancerhome'>
          FreelanceFlex
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
              <Link className='nav-link text-white' aria-current='page' to='/freelancer/vieworders'>
                Orders
              </Link>
            </li>
            <NavDropdown title="Gigs" id="gigs-dropdown">
              <NavDropdown.Item href='/freelancer/creategig'>Create Gig</NavDropdown.Item>
              <NavDropdown.Item href='#' onClick={viewAllGigs}>View Gigs</NavDropdown.Item>
            </NavDropdown>
            <Link className='nav-link text-white' aria-current='page' to='/freelancer/viewprofile'>
              Profile
            </Link>
            <li className='nav-item'>
              <button onClick={onLogout} className='nav-link btn btn-link text-white' aria-current='page'>
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Freelancernavbar;

