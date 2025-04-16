// src/styles/AuthStyles.js
import styled from 'styled-components';

export const AuthWrapper = styled.div`
  max-width: 400px;
  margin: 0 auto;
  margin-top: 10vh;
  background: white;
  padding: 2rem;
  border-radius: 1.5rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
`;

export const SpacedBox = styled.div`
  margin-bottom: 0.5rem;
`;

export const ErrorMessage = styled.p`
  color: #ef4444;
  font-size: 0.875rem; /* text-sm */
  margin-top: 0.5rem;  /* mt-2 */
  text-align: center;
`;

export const Input = styled.input`
  width: 100%;
  padding: 0.75rem 1rem;
    margin-bottom: ${(props) => props.marginBottom || '1rem'};  
  border: 1px solid #ddd;
  border-radius: 0.75rem;
  font-size: 1rem;
  color: #1a1a1a;
  background-color: #f9fafb;

  &:focus {
    outline: none;
    border-color: #6366f1;
    box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
  }
`;

export const Button = styled.button`
  width: 100%;
  padding: 0.75rem 1rem;
  background-color: #6366f1;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 0.75rem;
  cursor: pointer;
  transition: background 0.2s;

  &:hover {
    background-color: #4f46e5;
  }
`;

export const DuplicatedButton = styled.button`
    width: 100%;
    padding: 0.75rem 1rem;
    background-color: #6d6d85;
    color: white;
    font-weight: bold;
    border: none;
    border-radius: 0.75rem;
    cursor: pointer;
    transition: background 0.2s;
    margin-bottom: 1rem;

    &:hover {
        background-color: #535369;
    }
`;

export const SubLinks = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
`;

export const SubLink = styled.button`
  background: none;
  border: none;
  color: #6b7280;
  font-size: 0.9rem;
  text-decoration: underline;
  cursor: pointer;

  &:focus {
    outline: none;
  }

  &:hover {
    color: #374151;
  }
`;
