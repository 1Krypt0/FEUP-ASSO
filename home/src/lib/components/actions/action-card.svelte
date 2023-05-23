<script lang="ts">
	import { DropletIcon, PercentIcon, PowerIcon, ThermometerIcon, XIcon } from 'svelte-feather-icons';
	import RangeAction from './range-action.svelte';
	import ColorPickerAction from './color-picker-action.svelte';
    import SvelteTooltip from 'svelte-tooltip';

	export let device: any;
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

    function intensityToBeSet() {
        setIntensity = !setIntensity;
        setColour = false;
        checkTemperature = false;
    }
    function colourToBeSet() {
        setIntensity = false;
        setColour = !setColour;
        checkTemperature = false;
    }
    function toBeTurnedOn() {
        turnedOn = !turnedOn;
    }
    function TemperatureToBeChecked() {
        checkTemperature = !checkTemperature;
        setIntensity = false;
        setColour = false;
    }

    device.deviceActions.forEach(action => {
        switch (action.name){
            case "toggle":
            case "boolean":
                turnedOn = action.status == 1;
                break;
            case "range":
                min = action.properties.min;
                max = action.properties.max;
                step = action.properties.step;
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
        {#each device.deviceActions as deviceAction}
            {#if deviceAction.action.name == "toggle" || deviceAction.action.name == "boolean"}
                {#if turnedOn}
                    <SvelteTooltip tip="turn off" bottom >
                        <button on:click={toBeTurnedOn} class={`rounded-full p-1 ${turnedOn ? "bg-accent" : "bg-light"}`}><PowerIcon/></button>
                    </SvelteTooltip>
                {:else}
                    <SvelteTooltip tip="turn on" bottom >
                        <button on:click={toBeTurnedOn} class={`rounded-full p-1 ${turnedOn ? "bg-accent" : "bg-light"}`}><PowerIcon/></button>
                    </SvelteTooltip>
                {/if}
            {/if}
            {#if deviceAction.action.name == "range"}
                <SvelteTooltip tip={deviceAction.displayName} bottom >
                    <button on:click={intensityToBeSet} class={`rounded-full p-1 ${setIntensity ? "bg-accent" : "bg-light"}`}><PercentIcon/></button>
                </SvelteTooltip>
            {/if}
            {#if deviceAction.action.name == "rgb"}
                <SvelteTooltip tip={deviceAction.displayName} bottom >
                    <button on:click={colourToBeSet} class={`rounded-full p-1 ${setColour ? "bg-accent" : "bg-light"}`}><DropletIcon/></button>
                </SvelteTooltip>
            {/if}
            {#if deviceAction.action.name == "readonly-number"}
                <SvelteTooltip tip={deviceAction.displayName} bottom >
                    <button on:click={TemperatureToBeChecked} class={`rounded-full p-1 ${checkTemperature ? "bg-accent" : "bg-light"}`}><ThermometerIcon/></button>
                </SvelteTooltip>
            {/if}
        {/each}
    </div>
    <div class="h-full flex-col flex justify-center">
        {#if setIntensity}
            <RangeAction value={60} min={min} max={max} step={step} device_type="light"></RangeAction>
        {/if}
        {#if setColour}
            <ColorPickerAction hex={hex}></ColorPickerAction>
        {/if}
        {#if checkTemperature}
            <div class="text-2xl flex-row flex justify-center">
                {temperature}ºC
            </div>
        {/if}
    </div>
</section>