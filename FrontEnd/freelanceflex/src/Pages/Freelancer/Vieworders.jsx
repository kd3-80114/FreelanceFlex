// export function Vieworders()
// {
//     return(<>
//         <h1>Vieworders</h1>
        
//         </>)
// }
// export default Vieworders

// import React, { useState, useEffect } from 'react';
// import { getAllBuyerOrders } from '../../Services/Buyer';
// import Buyernavbar from '../../Components/Buyernavbar';
// import { Link } from 'react-router-dom';

// const OrderCard = ({ order }) => {
//   const { id, amount, startDate, deliveryDate } = order;

//   return (
//     <div className="card mb-3">
//       <div className="card-body">
//         <p className="card-text"><strong>Amount:</strong> ${amount}</p>
//         <p className="card-text"><strong>Start Date:</strong> {new Date(startDate).toLocaleString()}</p>
//         <p className="card-text"><strong>Delivery Date:</strong> {new Date(deliveryDate).toLocaleString()}</p>
//         <Link to={`buyer/make-review/${id}`} className="btn btn-primary">Make Review</Link>
//       </div>
//     </div>
//   );
// };

// const OrderList = () => {
//   const [orders, setOrders] = useState([]);
//   var currentbuyer = JSON.parse(sessionStorage['currentUser']);

//   useEffect(() => {
//     const fetchOrders = async () => {
//       try {
//         const response = await getAllBuyerOrders(currentbuyer.id);
//         setOrders(response.data);
//       } catch (error) {
//         console.error('Error fetching orders:', error);
//       }
//     };

//     fetchOrders();
//   }, []);

//   return (
//     <>
//       <Buyernavbar />
//       <div className="container mt-4">
//         <h1 className='title'>Orders</h1>
//         <div className="row">
//           {orders.map(order => (
//             <div key={order.id} className="col-md-6">
//               <OrderCard order={order} />
//             </div>
//           ))}
//         </div>
//       </div>
//     </>
//   );
// };

// export default OrderList;

import React, { useState, useEffect } from 'react';
import { getAllFreelancerOrders } from '../../Services/Freelancer';
import Freelancenavbar from '../../Components/Freelancenavbar';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const OrderCard = ({ order }) => {
  const { id, amount, startDate, deliveryDate } = order;
  const navigate = useNavigate();

  return (
    <div className="card mb-3">
      <div className="card-body">
        <p className="card-text"><strong>Amount:</strong> ${amount}</p>
        <p className="card-text"><strong>Start Date:</strong> {new Date(startDate).toLocaleString()}</p>
        <p className="card-text"><strong>Delivery Date:</strong> {new Date(deliveryDate).toLocaleString()}</p>
      </div>
    </div>
  );
};

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  var currentbuyer = JSON.parse(sessionStorage['currentUser']);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await getAllFreelancerOrders(currentbuyer.id);
        console.log(response.data)
        if(response.status!==200)
        {
            toast.warn("You dont have any orders yet..")
            navigate('/freelancer/freelancerhome')
        }
        else
        {
            setOrders(response.data)
        }
        

      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    };

    fetchOrders();
  }, []);

  return (
    <>
      <Freelancenavbar />
      <div className="container mt-4">
        <h1 className='title'>Orders</h1>
        <div className="row">
          {orders.map(order => (
            <div key={order.id} className="col-md-6">
              <OrderCard order={order} />
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default OrderList;
