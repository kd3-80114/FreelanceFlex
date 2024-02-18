import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { signupBuyer } from '../../Services/User'

export function Signup() {
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')
  
  const [contactNo, setContactNo] = useState('');
  const [description, setDescription] = useState('');
  //
  ///change this in backend if required
  //const [profilePicture, setProfilePicture] = useState(['string']);
  const profilePicture=[];
  
  // Address
  const [country, setCountry] = useState('');
  const [state, setState] = useState('');
  const [city, setCity] = useState('');
  const [landmark, setLandmark] = useState('');
  const [pincode, setPincode] = useState('');

  // SignIn
  const [userRole, setUserRole] = useState('ROLE_BUYER');


  // get the navigation function
  const navigate = useNavigate()

  const onSignup = async () => {
    if (firstName.length == 0) {
      toast.warn('enter first name')
    } else if (lastName.length == 0) {
      toast.warn('enter last name')
    } else if (email.length == 0) {
      toast.warn('enter email')
    } else if (password.length == 0) {
      toast.warn('enter password')
    } else if (confirmPassword.length == 0) {
      toast.warn('enter confirm password')
    } else if (password != confirmPassword) {
      toast.warn('password does not match')
    } else {
      // make the api call
      const result = await signupBuyer(firstName, lastName, email, password,
        contactNo,description,profilePicture,country,state,city,landmark,pincode,userRole)
        console.log(result);

      if (result.status==200) {
        console.log(result);
        toast.success('Successfully registered as Buyer')
        navigate('/signin')
      } else {
        toast.error(result['error'])
      }
    }
  }

  return (
    <>
      <h1 className='title'>Buyer Signup</h1>

      <div className='row'>
        <div className='col'></div>
        <div className='col'>
          <div className='form'>
            <div className='mb-3'>
              <label htmlFor=''>First Name</label>
              <input
                onChange={(e) => setFirstName(e.target.value)}
                type='text'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Last Name</label>
              <input
                onChange={(e) => setLastName(e.target.value)}
                type='text'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Email</label>
              <input
                onChange={(e) => setEmail(e.target.value)}
                type='email'
                placeholder='abc@test.com'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Password</label>
              <input
                onChange={(e) => setPassword(e.target.value)}
                type='password'
                placeholder='xxxxxxxx'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Confirm Password</label>
              <input
                onChange={(e) => setConfirmPassword(e.target.value)}
                type='password'
                placeholder='xxxxxxxx'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Contact Number</label>
              <input
                onChange={(e) => setContactNo(e.target.value)}
                type='tel'
                placeholder='+91-xxxxxxxx'
                className='form-control'
              />
            </div>
            <div className='mb-3'>
              <label htmlFor=''>Description</label>
              <input
                onChange={(e) => setDescription(e.target.value)}
                type='textarea'
                placeholder='Write something about yourself...'
                className='form-control'
              />
            </div>
            <div>
      <div className='mb-3'>
        <label htmlFor=''>Country</label>
        <input
          onChange={(e) => setCountry(e.target.value)}
          type='text'
          placeholder='Country'
          className='form-control'
        />
      </div>
      <div className='mb-3'>
        <label htmlFor=''>State</label>
        <input
          onChange={(e) => setState(e.target.value)}
          type='text'
          placeholder='State'
          className='form-control'
        />
      </div>
      <div className='mb-3'>
        <label htmlFor=''>City</label>
        <input
          onChange={(e) => setCity(e.target.value)}
          type='text'
          placeholder='City'
          className='form-control'
        />
      </div>
      <div className='mb-3'>
        <label htmlFor=''>Landmark</label>
        <input
          onChange={(e) => setLandmark(e.target.value)}
          type='text'
          placeholder='Landmark'
          className='form-control'
        />
      </div>
      <div className='mb-3'>
        <label htmlFor=''>Pincode</label>
        <input
          onChange={(e) => setPincode(e.target.value)}
          type='text'
          placeholder='Pincode'
          className='form-control'
        />
      </div>
            </div>
            <div className='mb-3'>
              <div>
                Already have an account? <Link to='/signin' >Sign In</Link>
              </div>
              <button onClick={onSignup} className='btn btn-primary mt-2'>
                Sign Up
              </button>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>
    </>
  )
}

export default Signup
