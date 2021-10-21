###Work with Java:

    [mehmet@localhost hb-mars-rover]$ java -jar ./target/hb-mars-rover-0.0.1-SNAPSHOT.jar 5 5 "1 2 N" LMLMLMLMM

###Work with Docker

Building Docker Image
    
    [mehmet@localhost hb-mars-rover]$ docker build -t image_name .

Running project on docker

    [mehmet@localhost hb-mars-rover] $docker run -d --name container_name image_name [container build option] 

To run Docker image use the docker run command as follows
    
    $docker run -d --name container_name image_name dimensionX dimensionY roverStartPosition1 roverInstruction1 roverStartPosition2 roverInstruction2 .......... roverStartPositionN roverInstructionN

    Parameters:
        dimensionX: X coordinate of plateau ---> 5
        dimensionY: Y coordinate of plateau ---> 5
        roverStartPosition1: Start Rover Position --> 2 3 N
        roverInstruction: Instructions --> LMLMLMLMM 

Usage

    docker build -t hb_mars_rover .

    docker run hb_mars_rover 5 5 "1 2 N" LMLMLMLMM

    docker run hb_mars_rover 5 5 "1 2 N" LMLMLMLMM "3 3 E" MMRMMRMRRM

