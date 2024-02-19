import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { placeOrder } from '../../Services/Buyer';
import {Buyernavbar} from '../../Components/Buyernavbar'
import { toast } from 'react-toastify'
export function ConfirmOrder() {
  const location = useLocation();
  const fid = location.state?.freelancerid;
  const gig = location.state?.gigtoforward;
  const currentBuyer=JSON.parse(sessionStorage['currentUser'])
  const { title, description, price, deliveryTime, category } = gig;
  // Calculate delivery date
  const deliveryDate = new Date();
  deliveryDate.setDate(deliveryDate.getDate() + (deliveryTime || 0));
    var navigate=useNavigate();
  // Function to handle placing the order
  const handlePlaceOrder = async () => {
    var result=await placeOrder(currentBuyer.id,currentBuyer.email,gig.id,deliveryTime,price,fid)
    navigate('/buyer/vieworders')
    toast.success('Your order has been placed.')
  };

  return (
    <>
    <Buyernavbar></Buyernavbar>
    <div className="container mt-4">
      <h1>Gig Details</h1>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{title}</h5>
          <p className="card-text">{description}</p>
          <p className="card-text"><strong>Price:</strong> ${price}</p>
          <p className="card-text"><strong>Delivery Date:</strong> {deliveryDate.toDateString()}</p>
          <p className="card-text"><strong>Category:</strong> {category}</p>
          <button className="btn btn-primary" onClick={handlePlaceOrder}>Confirm Order</button>
        </div>
      </div>
    </div>
    </>
  );
};

export default ConfirmOrder;
