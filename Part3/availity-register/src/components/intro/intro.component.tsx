import { useHistory } from "react-router-dom"
import './intro.styles.css';

export const Intro = () => {
   let hist = useHistory();
   let handleClick = () => {
      hist.push('/register');
   }
   return(
      <div className="intro">
         <h4>You can register today to become a part of the Availity system!</h4>
         <button className='regis-btn' type='button' onClick={handleClick}>Begin Registration</button>
      </div>
   );
}