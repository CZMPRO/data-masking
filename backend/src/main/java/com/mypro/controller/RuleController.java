package com.mypro.controller;

import com.mypro.entity.MaskingPolicy;
import com.mypro.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy")
public class RuleController {

    @Autowired
    private MyService maskingPolicyService;

    @GetMapping("/list")
    public List<MaskingPolicy> getAllPolicies() throws Exception {
        return maskingPolicyService.queryAllPolicies();
    }

    @PostMapping("/add")
    public MaskingPolicy addPolicy(@RequestBody MaskingPolicy policy) throws Exception {
        return maskingPolicyService.addPolicy(policy);
    }

    @PutMapping("/{policyId}")
    public MaskingPolicy updatePolicy(@PathVariable Integer policyId, @RequestBody MaskingPolicy policy) throws Exception {
        policy.setPolicyId(policyId);
        return maskingPolicyService.updatePolicy(policy);
    }

    @DeleteMapping("/{policyId}")
    public void deletePolicy(@PathVariable Integer policyId) throws Exception {
        maskingPolicyService.deletePolicy(policyId);
    }
}