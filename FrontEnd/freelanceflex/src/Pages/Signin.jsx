import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import signinUser from '../Services/User'
export function Signin()
{
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const navigate = useNavigate()

  const onSignin = async () => {
    if (email.length == 0) {
      toast.warn('Enter email')
    } else if (password.length == 0) {
      toast.warn('Enter password')
    } else {
      // make the api call
      const result = await signinUser(email, password)
      if (result.status==200) 
      {
        // cache the token
        const token = result['data']['signinResponse']['jwt']
        sessionStorage['token'] = token
        const user=result['data']['user']
        
        sessionStorage['currentUser']=JSON.stringify(user)
        console.log(user.role);
        if(user.role=='FREELANCER')
        {
            navigate('/freelancer/freelancerhome')
            toast.success('Welcome')
        }
        else if(user.role=='ADMIN')
        {
            navigate('/admin/adminhome')
            toast.success('Welcome')
        }
        else if(user.role=='BUYER')
        {
            navigate('/buyer/buyerhome')
            toast.success('Welcome')
        }  
      } else 
      {
        toast.warn("Invalid Credentials")
      }
    }
  }
    return(<>
        <h1 className="title">Sign In</h1>
        <div className='row'>
        <div className='col'></div>
        <div className='col'>
          <div className='form'>
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
              <div>
                Don't have an account? <Link to='/signup'>Signup here</Link>
              </div>
              <button onClick={onSignin} className='btn btn-primary mt-2'>
                Signin
              </button>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>   
        </>)
}
export default Signin