import { Jumbotron, Container, Image, Row } from 'react-bootstrap';
import dumbell from './dumbell.png';

function Homepage() {
    return (<div> 
        <Jumbotron fluid>
          <Container>
            <Row>
              <h1 style={{marginRight: 20}}>Welcome to Progress Pal!</h1> 
              <Image src={dumbell} rounded height="40" width="40"/>
            </Row>
            <p>
              Here you can view your workout and movement data.
            </p>
          </Container>
        </Jumbotron>
        </div>);
}

export default Homepage;