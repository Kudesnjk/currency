package main

import (
	"fmt"
	"io/ioutil"
	"math"
	"math/rand"
	"strconv"
	"strings"
)

const SEPARATOR = ";"
const NEW_LINE = "\n"

type CurrencyFabric struct {
	Currencies []Currency
}

func NewCurrencyFabric(filename string) (*CurrencyFabric, error) {
	buffer, err := ioutil.ReadFile(filename)
	if err != nil {
		return nil, err
	}

	lines := strings.Split(string(buffer), NEW_LINE)

	result := make([]Currency, len(lines))
	for idx, line := range lines {
		values := strings.Split(line, SEPARATOR)
		result[idx].ShortName = values[0]
		result[idx].Name = values[1]
		result[idx].Value, _ = strconv.ParseFloat(values[2], 64)
		result[idx].Image = values[3]
	}

	return &CurrencyFabric{
		Currencies: result,
	}, nil
}

func (cf *CurrencyFabric) MutateValues() {
	for idx, currency := range cf.Currencies {
		currValue := currency.Value
		if currValue != 1 {
			cf.Currencies[idx].Value = mutateNumber(currValue)
		}
	}
}

func mutateNumber(value float64) float64 {
	operation := rand.Int() % 2
	percent := float64(rand.Int()%2 + 1)
	if operation == 0 {
		return toFixed(value * (100 - percent) / 100, 2)
	}
	return toFixed(value * (100 + percent) / 100, 2)
}

func round(num float64) int {
	return int(num + math.Copysign(0.5, num))
}

func toFixed(num float64, precision int) float64 {
	output := math.Pow(10, float64(precision))
	return float64(round(num*output)) / output
}

func (cf *CurrencyFabric) PrintValues() {
	for _, value := range cf.Currencies {
		fmt.Println(value.Value)
	}
}
