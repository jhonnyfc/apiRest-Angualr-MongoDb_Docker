# https://medium.com/joolsoftware/how-to-set-up-an-angular-cli-project-with-docker-compose-a3ec78f179ab

# This defines our starting point
FROM node:14.16.0

WORKDIR /app/frontend
ENV PATH /app/frontend/node_modules/.bin:$PATH

COPY package.json /app/frontend/package.json
RUN npm install
RUN npm install -g @angular/cli@12.0.2

COPY . /app/frontend

EXPOSE 4200
CMD ng serve --host 0.0.0.0

# docker build -t testimage . -> para probar que funcion ael docker file