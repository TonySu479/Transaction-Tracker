#FROM node:18-alpine3.14 as build-step
#RUN mkdir -p /app
#WORKDIR /app
#COPY package.json /app
#RUN npm install
#COPY . /app
#RUN npm run build --prod
#FROM nginx:1.20.1
#COPY --from=build-step /app/dist/transaction-tracker-angular /usr/share/nginx/html
#EXPOSE 4201:80

#FROM node:alpine AS my-app-build
#WORKDIR /app
#COPY . .
#RUN npm ci && npm run build
#
#
#FROM nginx:alpine
#COPY --from=my-app-build /app/dist/transaction-tracker-angular /usr/share/nginx/html
#EXPOSE 80
