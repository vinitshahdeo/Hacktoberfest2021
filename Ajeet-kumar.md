name: Generate Chart Readme

on:
  pull_request:
    branches:
      - master
    paths:
      - 'bitnami/**/values.yaml'

jobs:
  generate-chart-readme:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout bitnami-labs/readme-generator-for-helm
        uses: actions/checkout@v2
        with:
          repository: 'bitnami-labs/readme-generator-for-helm'
          ref: '967764dacee72c05fc5d01c84c13ebb5dab7ac8b'
          path: readme-generator-for-helm

      - name: Cache node modules
        uses: actions/cache@v2
        env:
          cache-name: cache-node-modules
        with:
          # npm cache files are stored in `~/.npm` on Linux/macOS
          path: ~/.npm
          key: ${{ runner.os }}-build-${{ env.cache-name }}-${{ hashFiles('readme-generator-for-helm/package-lock.json') }}
          restore-keys: |
            ${{ runner.os }}-build-${{ env.cache-name }}-
      - name: Install readme-generator-for-helm dependencies
        run: cd readme-generator-for-helm && npm install

      - name: Checkout bitnami/charts
        uses: actions/checkout@v2
        with:
          path: charts

      - name: Get list of files with changes in Pull Request
        id: pr-file-changes
        uses: trilom/file-changes-action@v1.2.4
        with:
          fileOutput: ' '

      - name: Prepare readme-generator-for-helm inputs
        run: |
          cat $HOME/files.txt | tr " " "\n" | grep values\\.yaml > raw-inputs
          cat raw-inputs | sed 's/values.yaml/README.md/' >> raw-inputs
          cat raw-inputs | sed 's/bitnami/charts\/bitnami/' > prepared-inputs
      - name: Execute readme-generator-for-helm
        run: readme-generator-for-helm/bin/index.js -r $(cat prepared-inputs | grep README) -v $(cat prepared-inputs | grep values)

      - name: Output generated README.md
        run: cat $(cat prepared-inputs | grep README)
