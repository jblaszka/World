package simulation;

public record SimulationStatistics (
    int dayNumber,
    double meanLifeLenght,
    double meanChildrenNumber,
    double meanEnergy,
    int noOfAnimals,
    int noOfPlants){}