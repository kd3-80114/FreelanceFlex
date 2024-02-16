import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify'
import { Container, Row, Col, Card } from 'react-bootstrap';
import 'react-toastify/dist/ReactToastify.css'
import Home from './Pages/Home';
import Signup from './Pages/Signup';
import Signin from './Pages/Signin';
import Creategig from './Pages/Freelancer/Creategig';
import Freelacersignup from './Pages/Freelancer/Freelancersignup';
import Updateprofile from './Pages/Freelancer/Updateprofile';
import Vieworders from './Pages/Freelancer/Vieworders';
import Viewprofile from './Pages/Freelancer/Viewprofile';
import Freelancerhome from './Pages/Freelancer/FreelancerHome';
import AdminHome from './Pages/Admin/AdminHome';
import BuyerHome from './Pages/Buyer/BuyerHome';
import Buyersignup from './Pages/Buyer/Buyersignup';
import BuyerViewProfile from './Pages/Buyer/BuyerViewProfile';
import Buyerviewreviews from './Pages/Buyer/Buyerviewreviews';

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
<Route path='/freelancer/freelancersignup' element={<Freelacersignup></Freelacersignup>}/>
<Route path='/freelancer/updateprofile' element={<Updateprofile></Updateprofile>}/>
<Route path='/freelancer/vieworders' element={<Vieworders></Vieworders>}/>
<Route path='/freelancer/viewprofile' element={<Viewprofile></Viewprofile>}/>
<Route path='/freelancer/freelancerhome' element={<Freelancerhome></Freelancerhome>}/>
<Route path='/admin/adminhome' element={<AdminHome></AdminHome>}/>
<Route path='/buyer/buyerhome' element={<BuyerHome></BuyerHome>}/>
<Route path='/buyer/signup' element={<Buyersignup></Buyersignup>}/>
<Route path='/buyer/viewProfile' element={<BuyerViewProfile></BuyerViewProfile>}/>
<Route path='/buyer/viewReviews' element={<Buyerviewreviews></Buyerviewreviews>}/>

    </Routes>
    <ToastContainer></ToastContainer>
    </div>
  );
}

export default App;
