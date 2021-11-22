import urllib.request

works = ["marnie", #When Marnie Was There
         "kaguyahime", #The story of Princess Kaguya
         "kazetachinu", #The Wind Rises
         "kokurikozaka", #From Coquelicot-zaka
         "karigurashi", #Borrower Arrietty
         "ponyo", #Ponyo on the cliff
         "ged", #Tales from Earthsea
         "chihiro"] #Spirited Away

url_base = "http://www.ghibli.jp/gallery/"
local_base = "C:/Users/takur/Desktop/ghibli/"
ext = ".jpg "

for work in works:
    for i in range(50):
        name = work + str(i+1).zfill(3)
        url = url_base + name + ext
        local = local_base + name + ext
        
#        print(url)
#        print(local)

        urllib.request.urlretrieve(url,local)
