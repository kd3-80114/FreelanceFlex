import axios from 'axios'
import { createError, createUrl } from './utils'

export async function signinUser(email, password) {
    try {
      const url = createUrl('login/user')
      const body = {
        email,
        password,
      }
      const response = await axios.post(url, body)
      return {data:response.data,status:response.status}
    } catch (ex) {
      return createError(ex)
    }
  }


export async function signupUser(firstName, lastName, email, password,
    contactNo,description,profilePicture,country,state,city,landmark,pincode,
    primarySkill,secondarySkill,thirdSkill,fourthSkill,fifthSkill,userRole) {
  try {
    const url = createUrl('freelancer/signUp')
    const body = {
        firstName,
        lastName,
        email,
        password,
        contactNo,
        description,
        permanentAddress:{
          country,
          state,
          city,
          landmark,
          pincode,
        },
        skills:{
          primarySkill,
          secondarySkill,
          thirdSkill,
          fourthSkill,
          fifthSkill,
        },
        signIn:
        {
          email,
          password,
          userRole:"ROLE_FREELANCER",
        }
    }
    const response = await axios.post(url, body)
    return  {data:response.data,status:response.status}
  } catch (ex) {
    return createError(ex)
  }
}
export default signinUser