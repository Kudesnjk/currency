package main

import (
	"encoding/json"
	"fmt"
	"log"
	"math/rand"
	"net/http"
	"time"
)

const ADDRESS = "0.0.0.0:8080"

func main() {
	rand.Seed(time.Now().UTC().UnixNano())
	cf, err := NewCurrencyFabric("currencies.txt")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		cf.MutateValues()
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(cf.Currencies)
	})

	log.Fatal(http.ListenAndServe(ADDRESS, nil))
}