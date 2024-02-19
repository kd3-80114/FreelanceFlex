
import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import defaultImage from './defaultImage.jpg';
import {getFreelacerIdfromGigsId} from '../../Services/Buyer'

const GigCard = ({ gig }) => {
  const { id, title, description, price, deliveryTime, category, gigImage } = gig;
  const navigate = useNavigate(); // Initialize the useNavigate hook

  // Set image source based on whether gigImage is null or not
  const imageSrc = gigImage ? `data:image/jpeg;base64,${gigImage}` : defaultImage;
  
  // Function to handle navigation to another page
  const handlePlaceOrder = async () => {
    var result=await getFreelacerIdfromGigsId(id);
    var fid=result.data
    navigate('/buyer/confirmorder',{state:{freelancerid: fid,gigtoforward:gig}});
    //navigate('/freelancer/viewgigs', { state: { gigs: result } });  }

  };

  return (
    <div className="row-3">
      <div className="card h-100 gig-card">
        <img src={imageSrc} alt={title} className="card-img-top gig-image" style={{ height: "200px", objectFit: "cover" }} />
        <div className="card-body gig-details">
          <h5 className="card-title gig-title">{title}</h5>
          <p className="card-text gig-description">{description}</p>
          <p className="card-text gig-price">Price: ${price}</p>
          <p className="card-text gig-delivery-time">Delivery Time: {deliveryTime || 'Not specified'} days</p>
          <p className="card-text gig-category">Category: {category}</p>
          {/* Button to navigate to another page */}
          <button className="btn btn-primary" onClick={handlePlaceOrder}>Place Order</button>
        </div>
      </div>
    </div>
  );
};

export default GigCard;
