<script lang="ts">
	import { DropletIcon, PercentIcon, PowerIcon, ThermometerIcon, XIcon } from 'svelte-feather-icons';
	import RangeAction from './range-action.svelte';
	import ColorPickerAction from './color-picker-action.svelte';
    import SvelteTooltip from 'svelte-tooltip';

	export let device: any;
    
    let setIntensity = true;
    let setColour = false;
    let turnedOn = false;
    let checkTemperature = false;
    function intensityToBeSet() {
        setIntensity = true;
        setColour = false;
        checkTemperature = false;
    }
    function colourToBeSet() {
        setIntensity = false;
        setColour = true;
        checkTemperature = false;
    }
    function toBeTurnedOn() {
        turnedOn = !turnedOn;
    }
    function TemperatureToBeChecked() {
        checkTemperature = true;
        setIntensity = false;
        setColour = false;
    }
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
    {#each device.deviceActions as action}
        <div class="flex-row flex justify-center gap-4 py-6">
            {#if turnedOn}
                <SvelteTooltip tip="turn off" bottom >
                    <button on:click={toBeTurnedOn} class={`rounded-full p-1 ${turnedOn ? "bg-accent" : "bg-light"}`}><PowerIcon/></button>
                </SvelteTooltip>
            {:else}
                <SvelteTooltip tip="turn on" bottom >
                    <button on:click={toBeTurnedOn} class={`rounded-full p-1 ${turnedOn ? "bg-accent" : "bg-light"}`}><PowerIcon/></button>
                </SvelteTooltip>
            {/if}
            {#if action.name == "interface"}
                <SvelteTooltip tip="set intensity" bottom >
                    <button on:click={intensityToBeSet} class={`rounded-full p-1 ${setIntensity ? "bg-accent" : "bg-light"}`}><PercentIcon/></button>
                </SvelteTooltip>
            {/if}
            <SvelteTooltip tip="set colour" bottom >
                <button on:click={colourToBeSet} class={`rounded-full p-1 ${setColour ? "bg-accent" : "bg-light"}`}><DropletIcon/></button>
            </SvelteTooltip>
            <SvelteTooltip tip="check temperature" bottom >
                <button on:click={TemperatureToBeChecked} class={`rounded-full p-1 ${checkTemperature ? "bg-accent" : "bg-light"}`}><ThermometerIcon/></button>
            </SvelteTooltip>
        </div>
        <div class="h-full flex-col flex justify-center">
            {#if setIntensity}
                <RangeAction value={60} min={0} max={100} step={1} device_type="light"></RangeAction>
            {/if}
            {#if setColour}
                <ColorPickerAction hex="#8c2d19"></ColorPickerAction>
            {/if}
            {#if checkTemperature}
                <div class="text-2xl flex-row flex justify-center">
                    15ºC
                </div>
            {/if}
        </div>
    {/each}
</section>