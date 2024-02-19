import React, { useState } from 'react';
import { makeReview } from '../../Services/Buyer';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const MakeReview = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [rating, setRating] = useState();
  const navigate = useNavigate();
  const location = useLocation();
  const freelancerid = location.state?.fid;

  const handleSubmit = async () => {
    var buyerUser = JSON.parse(sessionStorage['currentUser']);
    console.log("fid : "+freelancerid + "buyerid :"+buyerUser.id)
    try {
      const response = await makeReview(buyerUser.id, freelancerid, title, description, rating);
      console.log('Review submitted successfully:', response);
      navigate('/buyer/buyerhome')
      toast.success('Your review has been added successfully..!')
    } catch (error) {
      console.error('Error submitting review:', error);
      // Handle error scenario
    }
  };

//   return (
//     <div className="container mt-4">
//       <h1 className="text-center">Make Review</h1>
//       <form>
//         <div className="mb-3">
//           <label htmlFor="title" className="form-label">Title</label>
//           <input
//             type="text"
//             className="form-control"
//             id="title"
//             value={title}
//             onChange={(e) => setTitle(e.target.value)}
//           />
//         </div>
//         <div className="mb-3">
//           <label htmlFor="description" className="form-label">Description</label>
//           <input
//             type="text"
//             className="form-control"
//             id="description"
//             value={description}
//             onChange={(e) => setDescription(e.target.value)}
//           />
//         </div>
//         <div className="mb-3">
//           <label htmlFor="rating" className="form-label">Rating</label>
//           <input
//             type="number"
//             className="form-control"
//             id="rating"
//             value={rating}
//             onChange={(e) => setRating(Number(e.target.value))}
//           />
//         </div>
//         <button type="button" className="btn btn-primary" onClick={handleSubmit}>Submit</button>
//       </form>
//     </div>
//   );
return (
    <div className="container mt-4">
      <h1 className="text-center mb-4">Make Review</h1>
      <form>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">Title</label>
          <input
            type="text"
            className="form-control"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="description" className="form-label">Description</label>
          <input
            type="text"
            className="form-control"
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="rating" className="form-label">Rating</label>
          <input
            type="number"
            className="form-control"
            id="rating"
            value={rating}
            onChange={(e) => setRating(Number(e.target.value))}
          />
        </div>
        <div className="text-center">
          <button type="button" className="btn btn-primary" onClick={handleSubmit}>Submit</button>
        </div>
      </form>
    </div>
  );
  
};

export default MakeReview;
