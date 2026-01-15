import { Button, DatePicker } from '@heroui/react'

function App() {

  return (
    <div className='bg-gray-900 w-screen h-screen flex justify-center content-center'>
      <Button color='primary' className='text-white'>HeroUI</Button>
      <DatePicker className="max-w-[284px]" label="Birth date" />;
    </div>
  )
}

export default App
