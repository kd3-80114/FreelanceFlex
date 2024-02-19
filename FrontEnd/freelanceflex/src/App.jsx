import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify'
import { Container, Row, Col, Card, Placeholder } from 'react-bootstrap';
import 'react-toastify/dist/ReactToastify.css'
import Home from './Pages/Home';
import Signup from './Pages/Signup';
import Signin from './Pages/Signin';
import Creategig from './Pages/Freelancer/Creategig';
import Updateprofile from './Pages/Freelancer/Updateprofile';
import Vieworders from './Pages/Freelancer/Vieworders';
import Viewprofile from './Pages/Freelancer/Viewprofile';
import Freelancerhome from './Pages/Freelancer/FreelancerHome';
import AdminHome from './Pages/Admin/AdminHome';
import BuyerHome from './Pages/Buyer/BuyerHome';




import BuyerProfile from './Pages/Admin/BuyerProfile';
import FreelancerProfile from './Pages/Admin/FreelancerProfile';
import Buyernavbar from './Components/Buyernavbar';
import Adminnavbar from './Components/Adminnavbar';


import AddGigImage from './Pages/Freelancer/AddGigImage'
import Viewgig from './Pages/Freelancer/Viewgig';
import ConfirmOrder from './Pages/Buyer/ConfirmOrder';
import Buyersignup from './Pages/Buyer/Buyersignup';
import BuyerViewProfile from './Pages/Buyer/BuyerViewProfile';
import Buyerviewreviews from './Pages/Buyer/Buyerviewreviews';
import ViewOrders from './Pages/Buyer/ViewOrder';
import PlaceOrder from './Pages/Buyer/PlaceOrder'
import MakeReview from './Pages/Buyer/MakeReview'


function App() {
  return (
    <div className="container-fluid">
    <Routes>
<Route index element={<Home/>}/>
<Route path='/' element={<Home/>}/>
<Route path='/home' element={<Home/>}/>
<Route path='/signup' element={<Signup></Signup>}/>
<Route path='/signin' element={<Signin></Signin>}/>
<Route path='/freelancer/creategig' element={<Creategig></Creategig>}/>
<Route path='/freelancer/updateprofile' element={<Updateprofile></Updateprofile>}/>
<Route path='/freelancer/vieworders' element={<Vieworders></Vieworders>}/>
<Route path='/freelancer/viewgigs' element={<Viewgig></Viewgig>}/>
<Route path='/buyer/makereview' element={<MakeReview></MakeReview>}/>
<Route path='/freelancer/viewprofile' element={<Viewprofile></Viewprofile>}/>
<Route path='/freelancer/freelancerhome' element={<Freelancerhome></Freelancerhome>}/>
<Route path='/admin/adminhome' element={<AdminHome></AdminHome>}/>
<Route path='/buyer/buyerhome' element={<BuyerHome></BuyerHome>}/>
<Route path='/buyer/signup' element={<Buyersignup></Buyersignup>}/>
<Route path='/buyer/placeorder' element={<PlaceOrder></PlaceOrder>}/>
<Route path='/buyer/viewProfile' element={<BuyerViewProfile></BuyerViewProfile>}/>
<Route path='/buyer/viewReviews' element={<Buyerviewreviews></Buyerviewreviews>}/>

<Route path='/admin/buyerprofile' element={<BuyerProfile/>}></Route>
<Route path='/admin/freelancerprofile' element={<FreelancerProfile/>}></Route>

<Route path='/freelancer/addgigimage' element={<AddGigImage></AddGigImage>}/>
<Route path='/buyer/confirmorder' element={<ConfirmOrder></ConfirmOrder>}/>
<Route path='/buyer/vieworders' element={<ViewOrders></ViewOrders>}/>


    </Routes>
    <ToastContainer></ToastContainer>
    </div>
  );
}

export default App;
