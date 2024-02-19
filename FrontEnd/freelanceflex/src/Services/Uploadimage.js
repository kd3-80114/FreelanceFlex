import React, { useState } from 'react';
import axios from 'axios';

const UploadImage = ({ id,fid }) => {
  const [image, setImage] = useState('');

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleUploadImage = () => {
    const formData = new FormData();
    formData.append('image', image);
    //This is wrong.
    // const headers = {
    //   Authorization: sessionStorage['Authorization'],
    // };
    //This is right.
    const headers = {
      headers: {
        Authorization: sessionStorage['Authorization'],
      },
    }


    axios
      .post(`https://localhost:8080/freelancer/gigs/images/${fid}/${id}`,formData,headers)
      .then((response) => {
        console.log('Image uploaded successfully:', response);
        // Handle success response
      })
      .catch((error) => {
        console.error('Error uploading image:', error);
        // Handle error response
      });
  };

  return (
    <div>
      <label htmlFor="image" className="form-label">
       
      </label>
      <input
        type="file"
        className="form-control"
        id="image"
        onChange={handleImageChange}
      />
      <button onClick={handleUploadImage} className="btn btn-primary mt-2">
        Upload
      </button>
    </div>
  );
};

export default UploadImage;
