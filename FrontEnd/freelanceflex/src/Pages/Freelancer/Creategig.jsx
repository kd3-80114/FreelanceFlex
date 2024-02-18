import React, { useState, useRef } from "react";
import { NavDropdown, Button } from "react-bootstrap";
import Freelancernavbar from "../../Components/Freelancenavbar";
import { toast } from 'react-toastify'
import { createnewgig } from "../../Services/Freelancer";
import { useNavigate } from "react-router-dom";
import Uploadimage from "../../Services/Uploadimage";

export function Creategig() {
    var user=JSON.parse(sessionStorage.getItem('currentUser'))
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [price, setPrice] = useState(0);
    var id=user.id;
    const navigate = useNavigate()

    const handleCategorySelect = (category) => {
        setSelectedCategory(category);
        // Perform any additional actions upon category selection
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Handle form submission here, e.g., send data to backend
        if (title.length === 0) {
            toast.warn('Enter gig Title')
        } else if (description.length === 0) {
            toast.warn('Enter description')
        } else if (selectedCategory === null) {
            toast.warn('Select Category')
        } else if (price <= 0) {
            toast.warn('Price cannot be zero or less ')
        } else {
            const result = await createnewgig(id, title, description, selectedCategory, price)
            if (result.status === 200) {
                navigate('/freelancer/viewgigs')
                toast.success("Gig Created Successfully")
            } else {
                toast.warn("Gig not created please try again later")
            }
        }
    }

   
    return (
        <>
            <Freelancernavbar />
            <h1 className="title">Create Gig</h1>
            <form onSubmit={handleSubmit}>
                <div className='row'>
                    <div className='col'></div>
                    <div className='col'>
                        <div className='form'>
                            <div className='mb-3'>
                                <label htmlFor='title'>Title</label>
                                <input
                                    onChange={(e) => setTitle(e.target.value)}
                                    type='text'
                                    id='title'
                                    value={title}
                                    placeholder='Insert gig title here...'
                                    className='form-control'
                                />
                            </div>
                            <div className='mb-3'>
                                <label htmlFor='description'>Description</label>
                                <input
                                    onChange={(e) => setDescription(e.target.value)}
                                    type='textarea'
                                    id='description'
                                    value={description}
                                    placeholder='Insert brief description here..'
                                    className='form-control'
                                />
                            </div>
                            <div className='mb-3'>
                                <label htmlFor='description'>Price</label>
                                <input
                                    onChange={(e) => setPrice(e.target.value)}
                                    type='number'
                                    id='price'
                                    value={price}
                                    placeholder='insert price of gig.'
                                    className='form-control'
                                />
                            </div>

                            <Uploadimage id={id}></Uploadimage>

                           
 
                            
                            
                            
                            <NavDropdown title={selectedCategory || 'Select Category'} id="gigs-dropdown">
                                <NavDropdown.Item onClick={() => handleCategorySelect('GRAPHICS_DESIGN')}>GRAPHICS_DESIGN</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('DIGITAL_MARKETING')}>DIGITAL_MARKETING</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('WRITING_TRANSLATION')}>WRITING_TRANSLATION</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('VIDEO_ANIMATION')}>VIDEO_ANIMATION</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('MUSIC_AUDIO')}>MUSIC_AUDIO</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('PROGRAMMING_TECH')}>PROGRAMMING_TECH</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('BUSINESS')}>BUSINESS</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('LIFESTYLE')}>LIFESTYLE</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('DATA')}>DATA</NavDropdown.Item>
                                <NavDropdown.Item onClick={() => handleCategorySelect('PHOTOGRAPHY')}>PHOTOGRAPHY</NavDropdown.Item>
                            </NavDropdown>
                            <Button type="submit">Submit</Button>
                        </div>
                    </div>
                    <div className='col'></div>
                </div>
            </form>
        </>
    );
}

export default Creategig;
