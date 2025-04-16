// src/components/TestBox.jsx
import styled from 'styled-components';

const Box = styled.div`
  background: pink;
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
  font-weight: bold;
`;

export default function TestBox() {
    return <Box>스타일드 컴포넌트 테스트 💅</Box>;
}
