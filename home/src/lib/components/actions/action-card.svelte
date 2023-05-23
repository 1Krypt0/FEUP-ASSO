<script lang="ts">
	import { DropletIcon, PercentIcon, PowerIcon, ThermometerIcon, XIcon } from 'svelte-feather-icons';
	import RangeAction from './range-action.svelte';
	import ColorPickerAction from './color-picker-action.svelte';
    import SvelteTooltip from 'svelte-tooltip';
	import type { Device } from '$lib/types/device';

	export let device: Device;
    console.log("device", device);
    
    let setIntensity = false;
    let setColour = false;
    let turnedOn = false;
    let checkTemperature = false;
    let min : number;
    let max : number;
    let step: number;
    let hex : string;
    let temperature : string;
    let intensity: number[] = [0];
    let intensityID: number = -1;
    let colorID: number = -1;

    const BASE_URL = 'http://localhost:8080';
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    async function updateData(actionID: string, value: string) {
        const res = await fetch(`${BASE_URL}/devices/${device.id}/value/${actionID}`, {
            method: "POST",
            body: JSON.stringify({
                value
            }),
            headers: headers
        });
    }

    function intensityToBeSet(id: number) {
        setIntensity = !setIntensity;
        setColour = false;
        checkTemperature = false;
        intensityID = id;
    }
    function sendIntensity(event) {
        const inten: number = event.detail.value[0]
        updateData(intensityID.toString(), inten.toString());
    }

    function sendColor(event) {
        const color: string = event.detail.hex;
        console.log(color);
        updateData(colorID, color.slice(1))
    }

    function colourToBeSet(id: number) {
        setIntensity = false;
        setColour = !setColour;
        checkTemperature = false;
        colorID = id;
        
    }
    function toBeTurnedOn(id: number, status: string) {
        turnedOn = !turnedOn;
        updateData(id.toString(), status);
    }
    function TemperatureToBeChecked() {
        checkTemperature = !checkTemperature;
        setIntensity = false;
        setColour = false;
    }

    device.actions.forEach(action => {
        switch (action.name){
            case "toggle":
            case "boolean":
                turnedOn = action.status == "1";
                break;
            case "range":
                min = action.properties.min as number;
                max = action.properties.max as number;
                step = action.properties.step as number;
                break;
            case "rgb":
                hex = action.status;
                break;
            case "temperature":
                temperature = action.status;
                break;
    }});
</script>

<section class="flex-col flex drop-shadow-lg bg-white text-xl px-6 py-6 h-3/6 w-2/6 rounded-3xl">
    <div class="flex-row flex justify-between">
        <div class="flex justify-normal text-2xl font-bold gap-3 items-center">
            <slot />
            {device.name}
        </div>
        <div>
            <a href="/room/1"><XIcon /></a> <!-- TODO: get roomID -->
        </div>
    </div>
    <div class="flex-row flex justify-center gap-4 py-6">
        {#each device.actions as deviceAction}
            {#if deviceAction.actionName == "toggle" || deviceAction.actionName == "boolean"}
                {#if turnedOn}
                    <SvelteTooltip tip="turn off" bottom >
                        <button on:click={toBeTurnedOn(deviceAction.id, "0")} class={`rounded-full p-1 ${turnedOn ? "bg-accent" : "bg-light"}`}><PowerIcon/></button>
                    </SvelteTooltip>
                {:else}
                    <SvelteTooltip tip="turn on" bottom >
                        <button on:click={toBeTurnedOn(deviceAction.id, "1")} class={`rounded-full p-1 ${turnedOn ? "bg-accent" : "bg-light"}`}><PowerIcon/></button>
                    </SvelteTooltip>
                {/if}
            {/if}
            {#if deviceAction.actionName == "range"}
                <SvelteTooltip tip={deviceAction.name} bottom >
                    <button on:click={intensityToBeSet(deviceAction.id)} class={`rounded-full p-1 ${setIntensity ? "bg-accent" : "bg-light"}`}><PercentIcon/></button>
                </SvelteTooltip>
            {/if}
            {#if deviceAction.actionName == "rgb"}
                <SvelteTooltip tip={deviceAction.name} bottom >
                    <button on:click={colourToBeSet(deviceAction.id)} class={`rounded-full p-1 ${setColour ? "bg-accent" : "bg-light"}`}><DropletIcon/></button>
                </SvelteTooltip>
            {/if}
            {#if deviceAction.actionName == "readonly-number"}
                <SvelteTooltip tip={deviceAction.name} bottom >
                    <button on:click={TemperatureToBeChecked} class={`rounded-full p-1 ${checkTemperature ? "bg-accent" : "bg-light"}`}><ThermometerIcon/></button>
                </SvelteTooltip>
            {/if}
        {/each}
    </div>
    <div class="h-full flex-col flex justify-center">
        {#if setIntensity}
            <RangeAction on:stop={sendIntensity} bind:value={intensity} min={min} max={max} step={step} device_type="light"></RangeAction>
            {/if}
        {#if setColour}
            <ColorPickerAction on:color={sendColor} hex={hex}></ColorPickerAction>
        {/if}
        {#if checkTemperature}
            <div class="text-2xl flex-row flex justify-center">
                {temperature}ºC
            </div>
        {/if}
    </div>
</section>