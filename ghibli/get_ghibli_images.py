import urllib.request
import os

works = ["marnie", #When Marnie Was There
         "kaguyahime", #The story of Princess Kaguya
         "kazetachinu", #The Wind Rises
         "kokurikozaka", #From Coquelicot-zaka
         "karigurashi", #Borrower Arrietty
         "ponyo", #Ponyo on the cliff
         "ged", #Tales from Earthsea
         "chihiro"] #Spirited Away

# path
url_base = "http://www.ghibli.jp/gallery/"
path = os.getcwd().replace('\\', '/') + '/gImages/'

# make path folder
os.makedirs(path, exist_ok=True)

# extender
ext = ".jpg"

for work in works:
    for i in range(50):
        name = work + str(i+1).zfill(3)
        url = url_base + name + ext
        local = path + name + ext
        
#        print(url)
#        print(local)

        urllib.request.urlretrieve(url,local)
