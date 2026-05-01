import os
import requests
import json

base_url="http://localhost:8000"

# Utility functions
def clear():
    os.system("cls" if os.name == "nt" else "clear")

def test():
    try:
        url = "http://localhost:5000/test"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(message)
        return response

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

   
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
        print("[3] Help")
        print("[0] Quit")
        print("--------------------------------------------")

        choice = input("Choose an option: ").strip().lower()
       
        match choice:
            case '1':
                games_status()
                #print("hello world")
            case '2':
                game_details()
            case '3':
                #test()
                help()
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
        print("[1] Data Worker")
        print("[2] Cipher Worker")
        print("[3] Logic Solver Worker")
        print("[4] Image Processing Worker")
        print("[0] Return")
        print("--------------------------------------------")

        choice = input("Choose an option: ").strip().lower()

        match choice:
            case '1':
                print("")#\n
                data_details()
            case '2':
                print("")
                cipher_details()
            case '3':
                print("")
                logic_details()
            case '4':
                print("")
                image_details()
            case '0':
                return
            case _:
                    print("Invalid option.")

        input("\nPress Enter to continue...")


def games_status():
    while True:
        clear()
        #print("")#\n
        print("--------------------------------------------")
        print("Game status")
        print("--------------------------------------------")
        print("[1] Data Worker")
        print("[2] Cipher Worker")
        print("[3] Logic Solver Worker")
        print("[4] Image Processing Worker")
        print("[0] Return")
        print("--------------------------------------------")

        choice = input("Choose an option: ").strip().lower()

        match choice:
            case '1':
                print("\n")
                data_status()
            case '2':
                print("\n")
                cipher_status()
            case '3':
                print("\n")
                logic_status()
            case '4':
                print("\n")
                image_status()
            case '0':
                return
            case _: 
                print("Invalid option.")

        input("\nPress Enter to continue...")

#status
def data_status():
    try:
        url = "http://localhost:5000/status_data"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Data worker's status: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def cipher_status():
    try:
        url = "http://localhost:5000/status_cipher"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Cipher worker's status: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def logic_status():
    try:
        url = "http://localhost:5000/status_logic"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Logic worker's status: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def image_status():
    try:
        url = "http://localhost:5000/status_image"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Image worker's status: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")


#details
def data_details():
    try:
        url = "http://localhost:5000/details_data"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Data worker's details: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def cipher_details():
    try:
        url = "http://localhost:5000/details_cipher"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Cipher worker's details: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def logic_details():
    try:
        url = "http://localhost:5000/details_logic"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Logic worker's details: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def image_details():
    try:
        url = "http://localhost:5000/details_image"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()['status']

        print(f"The Image worker's details: ", {message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")

def help():
    try:
        url = "http://localhost:5000/"
        response = requests.get(url,timeout=5)
        response.raise_for_status()
        message = response.json()

        print({message})
        return 

    except Exception as e:
        print(f"An unexpected error occurred in name: {e}")



if __name__ == "__main__":
    menu()
