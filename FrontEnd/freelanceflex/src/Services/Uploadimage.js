import React from "react";
import { useState } from "react";
import { createUrl } from "./utils";
import axios from 'axios'
function Uploadimage(id)
{
    const[image,setImage]=useState('')
    function handleImage(e)
    {
        console.log(e.target.files)
        setImage(e.target.files[0])
    }
    function handleUploadImage()
    {
        const formData=new FormData();
        formData.append('image',image);
        const headers = {
            headers: {
              Authorization: sessionStorage['Authorization'],
            },
          }
        axios.post('https://localhost:8080/freelancer/gigs/images/'+id.id, formData,headers)
            .then(response => {
                console.log('Image uploaded successfully:', response);
                // Handle success response
            })
            .catch(error => {
                console.error('Error uploading image:', error);
                // Handle error response
            });
        }
    return(
        <div>
            <input type='file' name='file' onChange={handleImage}></input>
            <button onClick={handleUploadImage}>Submit</button>
        </div>
    )
}
export default Uploadimage