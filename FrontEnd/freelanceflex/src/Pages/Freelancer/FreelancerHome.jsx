// // import Freelancernavbar from "../../Components/Freelancenavbar"


// // export function FreelancerHome()
// // {
// //     return(<>
// //        <Freelancernavbar></Freelancernavbar>
// //         <h1>FreelancerHome</h1>
        
// //         </>)
// // }
// // export default FreelancerHome

// import React from "react";
// import Freelancernavbar from "../../Components/Freelancenavbar";
// import "./FreelancerHome.css"; // Import CSS file for custom styling
// import { useNavigate } from "react-router-dom"

// function FreelancerHome() {

//     const navigate = useNavigate();

//     const handleSignUpClick = () => {
//         // Navigate to the signup page when the button is clicked
//         navigate("/signup");
//       };

//   return (
//     <>
//       <Freelancernavbar />
//       <div className="container mt-4">
//         <div className="row">
//         <div className="col-md-8">
//             <h1>Welcome to Our Freelancer Community</h1>
//             <p>
//               Are you ready to take your freelance career to the next level?
//               Look no further! Our platform offers a vibrant community of
//               talented freelancers and exciting opportunities waiting for you.
//             </p>
//             <p>
//               Discover a world of possibilities where your skills and expertise
//               are valued. Whether you're a seasoned professional or just
//               starting out, there's a place for you here.
//             </p>
//             <p>
//               Join us today and unlock access to:
//               <ul>
//                 <li>Diverse Projects</li>
//                 <li>Collaborative Environment</li>
//                 <li>Secure Payments</li>
//                 <li>Career Growth</li>
//               </ul>
//             </p>
//           </div>
//           <div className="col-md-4">
//             <div className="card">
//               <div className="card-body">
//                 <h5 className="card-title">Get Started</h5>
//                 <p className="card-text">
//                   Ready to start freelancing? Sign up now and join our
//                   community!
//                 </p>
//                 <button className="btn btn-primary" onClick={handleSignUpClick}>Sign Up</button>
//               </div>
//             </div>
//           </div>
//         </div>
//       </div>
//     </>
//   );
// }

// export default FreelancerHome;

import React from "react";
import { useNavigate } from "react-router-dom";
import Freelancernavbar from "../../Components/Freelancenavbar";
import backgroundImage from "./bg2.jpg"; // Import your background image
import './FreelancerHome.css'

function FreelancerHome() {
  const navigate = useNavigate();

  const handleSignUpClick = () => {
    navigate("/signup");
  };

  return (
    <>
      <Freelancernavbar />
      <div
        className="container-fluid p-0" 
        style={{
          backgroundImage: `url(${backgroundImage})`, 
          backgroundSize: "cover",
          backgroundPosition: "center",
          minHeight: "100vh",
          margin:0,
          padding:0,
          boxSizing:"border-box",

        }}
      >
        <div className="row justify-content-center align-items-center h-100">
          <div className="col-md-8 text-center text-white">
            <h1>Welcome to Our Freelancer Community</h1>
            <p>
              Are you ready to take your freelance career to the next level?
              Look no further! Our platform offers a vibrant community of
              talented freelancers and exciting opportunities waiting for you.
            </p>
            <p>
              Discover a world of possibilities where your skills and expertise
              are valued. Whether you're a seasoned professional or just
              starting out, there's a place for you here.
            </p>
            <p>
  Join us today and unlock access to:
  <br />
  <span className="text-left">Diverse Projects, Collaborative Environment, Secure Payments, Career Growth</span>
</p>

            <button
              className="btn btn-primary"
              onClick={handleSignUpClick}
            >
              Sign Up Now
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default FreelancerHome;
