import os
import requests
import json

base_url="http://localhost:8000"

# Utility functions
def clear():
    os.system("cls" if os.name == "nt" else "clear")
   
# Menu options
def display_game_status():
    url = f"{base_url}/game-status"

    try:
        response = requests.get(url, timeout=5)

        # Raise exception for HTTP errors (4xx, 5xx)
        response.raise_for_status()

        # Display the list of games and statuses
        games_list = response.json()['games']
        print("\n")
        print(f"|{'Game ID':^10}|{'Status':^15}|")
        print("|----------|---------------|")
       
        for game in games_list:
            print(f"| {game['game_id']:<9}| {game['status']:<14}|")
       
        return

    except requests.exceptions.RequestException as e:
        print(f"Error calling game-status endpoint: {e}")
        return

# Menu
def menu():
    while True:
        clear()
        print("--------------------------------------------")
        print("Escape Game UI")
        print("--------------------------------------------")
        print("[1] Get status of all games")
        print("[2] Get game details")
        print("[0] Quit")
        print("--------------------------------------------")

        choice = input("Choose an option: ").strip().lower()
       
        match choice:
            case '1':
                #display_game_status()
                print("hello world")
            case '2':
                game_details()
            case '0':
                return
            case _:
                print("Invalid option.")

        input("\nPress Enter to continue...")


def game_details():
    while True:
        clear()
        #print("")#\n
        print("--------------------------------------------")
        print("Game details")
        print("--------------------------------------------")
        print("[1] Cipher Worker")
        print("[2] Data Worker")
        print("[3] Logic Solver Worker")
        print("[4] Image Processing Worker")
        print("[0] Return")
        print("--------------------------------------------")

        choice = input("Choose an option: ").strip().lower()

        match choice:
            case '1':
                print("")#\n
                print("Task:")
                print("Decrypts a Caesar cipher")
                print("\n")
                print("Data Format:")
                print("")
            case '2':
                print("")#\n
                print("Task:")
                print("")
                print("\n")
                print("Data Format:")
                print("")
            case '3':
                print("")#\n
                print("Task:")
                print("Solve Boolean satisfiability problems ")
                print("\n")
                print("Data Format:")
                print("")
            case '4':
                print("")#\n
                print("Task:")
                print("Extract hidden information from images")
                print("\n")
                print("Data Format:")
                print("")
            case '0':
                return
            case _:
                    print("Invalid option.")

        input("\nPress Enter to continue...")


if __name__ == "__main__":
    menu()
