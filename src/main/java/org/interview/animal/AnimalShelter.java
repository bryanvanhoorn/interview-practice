package org.interview.animal;

public class AnimalShelter {

    private ArrayBackedAnimalStack storageStack;

    public AnimalShelter() {
        storageStack = new ArrayBackedAnimalStack();
    }

    public void enqueue(Animal animal) {
        storageStack.push(animal);
    }

    public Animal dequeueAny() {
        ArrayBackedAnimalStack tempStack = new ArrayBackedAnimalStack();

        while (storageStack.isEmpty() == false) {
            tempStack.push(storageStack.pop());
        }

        Animal poppedAnimal = tempStack.pop();

        while (tempStack.isEmpty() == false) {
            storageStack.push(tempStack.pop());
        }

        return poppedAnimal;
    }

    public Dog dequeueDog() {
        ArrayBackedAnimalStack tempStack = new ArrayBackedAnimalStack();

        while (storageStack.isEmpty() == false) {
            tempStack.push(storageStack.pop());
        }

        Dog poppedDog = null;

        while (tempStack.isEmpty() == false) {
            Animal poppedAnimal = tempStack.pop();
            if (poppedDog == null && poppedAnimal instanceof Dog) {
                poppedDog = (Dog) poppedAnimal;
            } else {
                storageStack.push(poppedAnimal);
            }
        }

        return poppedDog;
    }

    public Cat dequeueCat() {
        ArrayBackedAnimalStack tempStack = new ArrayBackedAnimalStack();

        while (storageStack.isEmpty() == false) {
            tempStack.push(storageStack.pop());
        }

        Cat poppedCat = null;

        while (tempStack.isEmpty() == false) {
            Animal poppedAnimal = tempStack.pop();
            if (poppedCat == null && poppedAnimal instanceof Cat) {
                poppedCat = (Cat) poppedAnimal;
            } else {
                storageStack.push(poppedAnimal);
            }
        }

        return poppedCat;
    }
}
