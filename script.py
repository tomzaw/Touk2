import urllib.request
import urllib.parse
import json
from datetime import date

today = str(date.today())

response1 = urllib.request.urlopen("http://localhost:8080/screenings?date=" + today)
response2 = urllib.request.urlopen("http://localhost:8080/seats?roomId=1")
response3 = urllib.request.urlopen("http://localhost:8080/seats?screeningId=2")
response4 = urllib.request.urlopen("http://localhost:8080/orders/" +
"ticketPrice?screeningId=2&ticketType=CHILD&voucher=code2")
response5 = urllib.request.urlopen("http://localhost:8080/orders/" +
"ticketPrice?screeningId=2&ticketType=STUDENT&voucher=code2")

headers = {'Content-Type':'application/json'}
json1 = {'email':'clinet2@foo.com','name':'Clientb','surname':'Client_Client'}
data1=json.dumps(json1).encode('utf8')
req1 = urllib.request.Request("http://localhost:8080/clients", data1, headers, method='POST')

response6 = urllib.request.urlopen(req1)

json2={'appUserId':1,'clientId':1,'description':'string','paymentType':'string',
       'screeningId':2,'tickets':[{'seatId':1,'type':'CHILD'},
                                 {'seatId':4,'type':'STUDENT'}],
       'title':'string','type':'RESERVATION'}
data2=json.dumps(json2).encode('utf8')
req2 = urllib.request.Request("http://localhost:8080/orders?voucher2=code2", data2, headers, method='POST')

response7 = urllib.request.urlopen(req2)

response8 = urllib.request.urlopen("http://localhost:8080/orders/1/expirationTime")

print(response1.read())
print(response2.read())
print(response3.read())
print(response4.read())
print(response5.read())
print(response6.read())
print(response7.read())
print(response8.read())

