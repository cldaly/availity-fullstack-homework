import { useReducer, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { UserRegistration } from '../../models/UserRegistration';
import './register.styles.css';

const formReducer = (state: any, action: any) => {
   return {
      ...state,
      [action.name]: action.value
   }
}

export const Register = () => {
   const history = useHistory();
   const [formData, setFormData] = useReducer(formReducer, {});
   const [submitting, setSubmitting] = useState<boolean>(false);
   const [loading, setLoading] = useState<boolean>(false);
   const [completed, setCompleted] = useState<boolean>(false);
   const [isInvalid, setIsInvalid] = useState<boolean>(false);

   const handleInputChange = (event: any) => {
      setIsInvalid(false);
      setFormData({
         name: event.target.name,
         value: event.target.value
      });
   }

   const validate = (event: any) => {
      event.preventDefault();
      setIsInvalid(false);
      setSubmitting(true);
      const newUser: UserRegistration = formData;
      if (newUser && newUser.firstName && newUser.lastName && newUser.npi && newUser.address1 && newUser.address2 && newUser.city && newUser.state && newUser.zip && newUser.phone && newUser.email) {
         register(newUser);
      } else {
         setIsInvalid(true);
      }
   }

   const register = (newUser: UserRegistration) => {
      setLoading(true);
      setTimeout(() => {
         console.log("Results from new user registration:: ", newUser);
         setLoading(false);
         setCompleted(true);
      }, 4000);
   }

   const navigateHome = () => {
      history.push("/");
   }

   return(
      <div className="register">
         <h4>Complete the form below to register</h4>
         <form className='registration-form' onSubmit={validate}>
            <div className="form-row">
               <label className={submitting && !formData['firstName'] ? 'missing-input' : ''} htmlFor='firstName'>First Name</label>
               <input disabled={loading} type='text' id='firstName' name='firstName' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['lastName'] ? 'missing-input' : ''} htmlFor='lastName'>Last Name</label>
               <input disabled={loading} type='text' id='lastName' name='lastName' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['npi'] ? 'missing-input' : ''} htmlFor='npi'>NPI Number</label>
               <input disabled={loading} type='text' id='npi' name='npi' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['address1'] ? 'missing-input' : ''} htmlFor='address1'>Address Line 1</label>
               <input disabled={loading} type='text' id='address1' name='address1' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['address2'] ? 'missing-input' : ''} htmlFor='address2'>Address Line 2</label>
               <input disabled={loading} type='text' id='address2' name='address2' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['city'] ? 'missing-input' : ''} htmlFor='city'>City</label>
               <input disabled={loading} type="text" id='city' name='city' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['state'] ? 'missing-input' : ''} htmlFor='state'>State</label>
               <input disabled={loading} type="text" id='state' name='state' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['zip'] ? 'missing-input' : ''} htmlFor='zip'>Zip Code</label>
               <input disabled={loading} type="text" id='zip' name='zip' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['phone'] ? 'missing-input' : ''} htmlFor='phone'>Phone Number</label>
               <input disabled={loading} type='text' id='phone' name='phone' onChange={handleInputChange} />
            </div>
            <div className="form-row">
               <label className={submitting && !formData['email'] ? 'missing-input' : ''} htmlFor='email'>Email Address</label>
               <input disabled={loading} type="email" id='email' name='email' onChange={handleInputChange} />
            </div>
            {isInvalid && <div className="err-msg">Please make sure the form is complete!</div>}
            <div className="btn-grp">
               <button className={loading ? 'submit-btn disable-btn' : 'submit-btn'} disabled={loading}>Submit{loading && <span className='loading'></span>}</button>
            </div>
         </form>
         {completed && 
         <div className="modal" onClick={navigateHome}>
            <div className="modal-body">
               <span className="close-modal">&times;</span>
               <div className="modal-content">
                  <div className="modal-content-title">You are now registered with Availity!</div>
                  <div className="modal-content-text">Our team will reach out in the next 1-2 business days in order to begin integration</div>
               </div>
            </div>
         </div>
         }
      </div>
   );
}