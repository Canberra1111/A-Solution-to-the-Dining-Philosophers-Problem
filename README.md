# A-Solution-to-the-Dining-Philosophers-Problem

## Problem Statement

A number (N) of philosophers sit around a circular table, with only N forks on the table, one between each pair of philosophers. Each philosopher does two things in their life:

1. **Think**
2. **Eat Spaghetti**

Philosophers may only pick up the forks to their immediate left or right. Eating requires two forks. The challenge is to model the behavior of the philosophers in a way that avoids deadlock and ensures that no philosopher is indefinitely hungry.

---

## Problem Constraints:

- **Philosophers must alternate between thinking and eating.**
- **Eating requires two forks**, and each philosopher can only pick up the forks to their immediate left and right.
- **Deadlock Prevention**: Ensure no philosopher starves or gets stuck indefinitely.

---

## Approach

The solution uses the following elements to simulate the philosophers' behavior:

- **Philosopher Class**: Each philosopher is modeled as a separate thread that alternates between thinking and eating.
- **Forks (Semaphores)**: Semaphores are used to control access to the forks and ensure that only two philosophers can eat at the same time using the same fork.
- **Mutex**: A mutex is used to ensure proper synchronization when philosophers attempt to pick up or release forks.

Key actions:
1. **Think**: A philosopher spends some time thinking before trying to eat.
2. **Get Forks**: A philosopher picks up the forks to their left and right.
3. **Eat**: After acquiring the forks, the philosopher eats.
4. **Release Forks**: After eating, the philosopher releases the forks so others can use them.
