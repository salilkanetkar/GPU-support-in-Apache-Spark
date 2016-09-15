#include <iostream>
#include <boost/compute/core.hpp>

namespace compute = boost::compute;

int main()
{
    // get the default device
    compute::device device = compute::system::default_device();

    // print the device's name
    std::cout << "hello from " << device.name() << std::endl;

    return 0;
}

/*

This took bloody forever.
Step 1 - Install boost (home brew)
Step 2 - git clone compute into the local directory and voila.
--> g++ -Icompute/include hello.cpp -o hello -framework OpenCL
--> ./hello
*/
