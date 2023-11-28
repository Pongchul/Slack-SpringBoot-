import React from 'react';
import { createRoot } from 'react-dom/client';
import App from '@/layouts/App';
import { BrowserRouter } from 'react-router-dom';

async function main() {


  const root = createRoot(document.getElementById('root') as HTMLElement);

  root.render(
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}

main();