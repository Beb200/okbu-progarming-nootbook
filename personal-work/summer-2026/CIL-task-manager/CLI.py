import os
import json

def clear():
    os.system("cls" if os.name == "nt" else "clear")

def menu():
    try:
        while True:
            clear()
            print("--------------------------------------------")
            print("Escape Game UI")
            print("--------------------------------------------")
            print("[1] List")
            print("[2] Add")
            print("[3] ")
            print("[4] delete")
            print("[0] Quit")
            print("--------------------------------------------")

            choice = input("Choose an option: ").strip().lower()
        
            match choice:
                case '1':
                    print("1")
                    list()
                case '2':
                    print("2")
                    add()
                case '3':
                    print("3")
                case '4':
                    print("4")
                    delete()
                case '9':
                    print("test")
                case '0':
                    return
                case _:
                    print("Invalid option.")

            input("\nPress Enter to continue...")

    except Exception as e:
        print(f"An unexpected error occurred in menu: {e}")

def list():
    try:
        print()
        with open('info.json', 'r') as file:
            list = json.load(file)
        print(list)
    except Exception as e:
        print(f"An unexpected error occurred in list: {e}")

def add():
    try:
        clear()
        with open('info.json', 'r') as file:
            data = json.load(file)
        # data = {}
        while True:
            print()
            task = input("What is the name of the task? ")
            print()
            details = input("what are the details of the task? ")
            print()

            data[task] = details

            if input("Do you need to add another task? Y/N ") == ("n" or "N"):
                with open('info.json', 'w') as file:
                   json.dump(data, file, indent=4)
                print(data)
                return
            else:
                continue
    except Exception as e:
        print(f"An unexpected error occurred in list: {e}")

def delete():
    try:
        with open('info.json', 'r') as file:
            data = json.load(file)
        clear
        while True:
            print()
            d_task = input("What is the task that you are deleting? ")

            d_data = data.pop(d_task, None)

            print()#/n
            print(d_data)

            if d_data == None:
                print()#/n
                print("No task found. Please try again.")
                continue
            elif input("Do you want to delete another task? Y/N ") == ("n" or "N"):
                with open('info.json', 'w') as file:
                   json.dump(data, file, indent=4)
                print(data)
                return
            else:
                continue


    
    except Exception as e:
        print(f"An unexpected error occurred in list: {e}")
    

if __name__ == "__main__":
    menu()