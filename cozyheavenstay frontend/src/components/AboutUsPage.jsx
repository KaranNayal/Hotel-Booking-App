import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import chsLogo from './chs.jpeg'; 

const AboutUsPage = () => {
  return (
    <>
      <Navbar>
        <Logo to="/">
          <LogoImg src={chsLogo} alt="CozyHeavenStay Logo" />
        </Logo>
      </Navbar>
      <AboutSection>
        <Container>
          <ContentWrapper>
            <HeaderText>Welcome to <CursiveSpan>CozyHeavenStay</CursiveSpan></HeaderText>
            <DescriptionText>
              At CozyHeavenStay, we believe in the magic of travel and the power of hospitality. Our mission is to create unforgettable experiences for every guest, providing a home away from home in every corner of the world.
            </DescriptionText>
            <QuoteText>
              &quot;Travel is more than just seeing new places; it&apos;s about creating memories that last a lifetime.&quot;
            </QuoteText>
            <ContactButton>Contact us</ContactButton>
          </ContentWrapper>
        </Container>
      </AboutSection>
    </>
  );
}

const Navbar = styled.nav`
  background-color: #fff; /* Set background color for navbar */
  padding: 20px; /* Add padding */
`;

const Logo = styled(Link)`
  display: inline-block;
`;

const LogoImg = styled.img`
  width: 80px; /* Adjust the width of the logo */
  height: auto;
`;

const AboutSection = styled.section`
  background-image: url("https://cdn.pixabay.com/photo/2015/10/13/15/19/bodrum-986308_1280.jpg"); /* Use the imported image as background */
  background-size: cover;
  background-position: center;
  padding: 100px 0;
`;

const Container = styled.div`
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ContentWrapper = styled.div`
  text-align: center; /* Align text to the center */
  color: #fff; /* Set text color to white for better visibility on the background image */
`;

const HeaderText = styled.h1`
  font-size: 36px;
  margin-bottom: 20px;
`;

const CursiveSpan = styled.span`
  font-family: 'cursive'; /* Change font to cursive */
`;

const DescriptionText = styled.p`
  font-size: 18px;
  margin-bottom: 40px;
`;

const QuoteText = styled.blockquote`
  font-style: italic;
  font-size: 20px;
  margin-bottom: 40px;
`;

const ContactButton = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  font-size: 18px;
  cursor: pointer;
  transition: background-color 0.3s;
  text-decoration: none;
  border-radius: 5px;
  &:hover {
    background-color: #0056b3;
  }
`;

export default AboutUsPage;
