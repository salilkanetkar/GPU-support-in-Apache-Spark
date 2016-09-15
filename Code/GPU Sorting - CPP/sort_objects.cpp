//---------------------------------------------------------------------------//
// Copyright (c) 2013 Kyle Lutz <kyle.r.lutz@gmail.com>
//
// Distributed under the Boost Software License, Version 1.0
// See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt
//
// See http://boostorg.github.com/compute for more information.
//---------------------------------------------------------------------------//

#include <algorithm>
#include <iostream>
#include <vector>

#include <boost/compute/system.hpp>
#include <boost/compute/algorithm/copy.hpp>
#include <boost/compute/algorithm/sort.hpp>
#include <boost/compute/container/vector.hpp>

namespace compute = boost::compute;
#define BUFFERSIZE 1024

int rand_int()
{
    return rand() % 100;
}

// this example demonstrates how to sort a vector of ints on the GPU
int main()
{
    std::vector<float> host_vector;
    char buffer[BUFFERSIZE];
    FILE * pFile;
    pFile = fopen ("myfile.bin", "wb");
    while(fgets(buffer, BUFFERSIZE , stdin) != NULL) {
      fwrite (buffer , sizeof(char), sizeof(buffer), pFile);
      std::cout << rand_int() << "\n";
    }
    fclose (pFile);
    return 0;
}


/*
Compilation is -
g++ -Icompute/include sort.cpp -o sort -framework OpenCL
*/
