package main

type Currency struct {
	ShortName string  `json:"short_name"`
	Name      string  `json:"name"`
	Value     float64 `json:"value"`
	Image     string  `json:"image"`
}