import Workspace from '@/layouts/Workspace';
import React from 'react';
import { useParams } from 'react-router-dom';

const Channel = () => {
  const { workspace, channel } = useParams<{ workspace: string; channel: string }>();
  return (
      <div> Congratulation to login ! </div>
  )
}

export default Channel;