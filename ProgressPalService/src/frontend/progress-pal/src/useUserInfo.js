import { useState } from 'react';

export function useUserInfo() {
  const getUserId = () => {
    const userIdString = sessionStorage.getItem('userId');
    const userId = JSON.parse(userIdString);
    return userId  ? userId : undefined;
  };

  const [userId, setUserId] = useState(getUserId());

  const saveUserId = userId => {
    sessionStorage.setItem('userId', JSON.stringify(userId));
    setUserId(userId);
  };

  return {
    setUserId: saveUserId,
    userId
  }
}